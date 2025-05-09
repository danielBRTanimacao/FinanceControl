import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "@/views/user/Dashboard.vue";
import Login from "@/views/auth/Login.vue";
import Register from "@/views/auth/Register.vue";
import IndexPage from "@/views/user/IndexPage.vue";

const routes = [
    { path: "/", component: IndexPage },
    { path: "/painel", component: Dashboard },
    { path: "/login", component: Login },
    { path: "/registro", component: Register },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
