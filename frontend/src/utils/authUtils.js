export function isLoggedIn() {
    const token = getCookie("token");
    return !!token;
}

export function getCookie(name) {
    const cookies = document.cookie.split(";");
    for (let i = 0; i < cookies.length; i++) {
        const [key, value] = cookies[i].trim().split("=");
        if (key === name) {
            return value;
        }
    }
    return null;
}
