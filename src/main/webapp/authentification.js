const REGISTER_URL = 'http://localhost:8080/auth/register'
const LOGIN_URL = 'http://localhost:8080/auth/login'

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

    const signupForm = document.getElementById('signup-form')
    const signupError = document.getElementById('signup-error')
    const signupSuccess = document.getElementById('signup-success')

    if (!loginForm || !signupForm) return

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

    signupForm.addEventListener('submit', async (event) => {
        event.preventDefault()
        signupError.style.display = 'none'
        signupSuccess.style.display = 'none'

        const payload = {
            pseudo: document.getElementById('signup-pseudo').value.trim(),
            nom: document.getElementById('signup-nom').value.trim(),
            prenom: document.getElementById('signup-prenom').value.trim(),
            dateNaissance: document.getElementById('signup-date').value,
            mdp: document.getElementById('signup-mdp').value,
            statut: document.getElementById('signup-statut').value,
            adresse: document.getElementById('signup-adresse').value.trim()
        }

        try {
            const res = await fetch(REGISTER_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            })

            if (!res.ok) {
                const err = await res.json().catch(() => ({}))
                throw new Error(err.message || `Erreur ${res.status}`)
            }

            showMessage(signupError, signupSuccess, 'Inscription réussie.', true)
            signupForm.reset()
            document.getElementById('signup-statut').value = 'ACTIF'
        } catch (e) {
            showMessage(signupError, signupSuccess, e.message || "Erreur lors de l'inscription.", false)
        }
    })
})
