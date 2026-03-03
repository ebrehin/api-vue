const REGISTER_URL = GATEWAY_URL + '/auth/register'
const LOGIN_URL    = GATEWAY_URL + '/auth/login'

function showMessage(errorEl, successEl, message, isSuccess) {
    if (isSuccess) {
        errorEl.style.display = 'none'
        successEl.textContent = message
        successEl.style.display = 'block'
    } else {
        successEl.style.display = 'none'
        errorEl.textContent = message
        errorEl.style.display = 'block'
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form')
    const loginError = document.getElementById('login-error')
    const loginSuccess = document.getElementById('login-success')


    if (!loginForm) return

    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault()
        loginError.style.display = 'none'
        loginSuccess.style.display = 'none'

        const payload = {
            pseudo: document.getElementById('login-pseudo').value.trim(),
            mdp: document.getElementById('login-password').value
        }

        try {
            const res = await fetch(LOGIN_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            })

            if (!res.ok) {
                const err = await res.json().catch(() => ({}))
                throw new Error(err.message || `Erreur ${res.status}`)
            }

            const data = await res.json().catch(() => ({}))
            if (data && data.token) {
                localStorage.setItem('authToken', data.token)
            }
            showMessage(loginError, loginSuccess, 'Connexion réussie.', true)
        } catch (e) {
            showMessage(loginError, loginSuccess, e.message || 'Erreur lors de la connexion.', false)
        }
    })
})
