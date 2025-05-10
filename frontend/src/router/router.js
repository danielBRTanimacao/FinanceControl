import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "@/views/user/Dashboard.vue";
import Login from "@/views/auth/Login.vue";
import Register from "@/views/auth/Register.vue";
import IndexPage from "@/views/user/IndexPage.vue";

import { isLoggedIn } from "@/utils/authUtils";

const routes = [
    {
        path: "/",
        component: IndexPage,
    },
    {
        path: "/painel",
        component: Dashboard,
        meta: { requiresAuth: true },
    },
    {
        path: "/login",
        component: Login,
        meta: { guest: true },
    },
    {
        path: "/registro",
        component: Register,
        meta: { guest: true },
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const loggedIn = isLoggedIn();

    if (to.meta.requiresAuth && !loggedIn) {
        return next("/login");
    }

    if (to.meta.guest && loggedIn) {
        return next("/painel");
    }

    next();
});

export default router;
