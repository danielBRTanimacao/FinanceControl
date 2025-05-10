export function isLoggedIn() {
    const token = localStorage.getItem("token");
    return !!token;
}
