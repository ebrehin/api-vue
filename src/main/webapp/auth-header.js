/**
 * Bandeau d'authentification partagé — injecté automatiquement sur toutes les pages.
 * Lit authToken et authPseudo dans localStorage.
 */
;(function () {
    function extractPseudoFromToken(token) {
        try {
            var payload = token.split('.')[1]
            var decoded = JSON.parse(atob(payload))
            return decoded.sub || null
        } catch (e) { return null }
    }

    function init() {
        var token  = localStorage.getItem('authToken')
        var pseudo = localStorage.getItem('authPseudo')

        // Fallback : extraire le pseudo du JWT si pas en localStorage
        if (token && !pseudo) {
            pseudo = extractPseudoFromToken(token)
            if (pseudo) localStorage.setItem('authPseudo', pseudo)
        }

        var bar = document.createElement('div')
        bar.className = 'auth-header'

        if (token && pseudo) {
            bar.innerHTML =
                '<span class="auth-status">' +
                    '<span class="auth-dot connected"></span> ' +
                    'Connecté : <strong>' + pseudo.replace(/</g, '&lt;') + '</strong>' +
                '</span>' +
                '<button class="btn-logout" id="btn-logout">Déconnexion</button>'
        } else {
            bar.innerHTML =
                '<span class="auth-status">' +
                    '<span class="auth-dot disconnected"></span> Non connecté' +
                '</span>' +
                '<a class="btn-login" href="authentification.html">Se connecter</a>'
        }

        document.body.insertBefore(bar, document.body.firstChild)

        var logoutBtn = document.getElementById('btn-logout')
        if (logoutBtn) {
            logoutBtn.addEventListener('click', function () {
                localStorage.removeItem('authToken')
                localStorage.removeItem('authPseudo')
                window.location.reload()
            })
        }
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', init)
    } else {
        init()
    }
})()
