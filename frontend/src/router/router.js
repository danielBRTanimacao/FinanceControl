import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "@/views/user/Dashboard.vue";
import Login from "@/views/auth/Login.vue";
import Register from "@/views/auth/Register.vue";

const routes = [
    { path: "/", component: Dashboard },
    { path: "/login", component: Login },
    { path: "/registro", component: Register },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
