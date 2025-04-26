<script>
import MailIcon from "../../assets/imgs/icons/icon-mail.png";
import PasswordIcon from "../../assets/imgs/icons/icon-password.png";

export default {
    data() {
        return {
            icon: MailIcon,
            iconPassword: PasswordIcon,
            showPassword: false,
        };
    },
    methods: {
        togglePassword() {
            this.showPassword = !this.showPassword;
        },
    },
};
</script>

<template>
    <main class="w-full h-[100dvh] bg-black mainForm lg:flex">
        <article class="hidden w-300 lg:block"></article>
        <form
            class="glassmorphism w-full h-[100dvh] flex justify-center items-center text-white"
            method="post"
        >
            <div class="flex flex-col gap-10 w-80 sm:w-auto">
                <div class="sm:hidden text-6xl text-center">
                    <span class="font-extrabold">Bem</span>
                    {{ " " }}
                    <span class="font-semibold text-gray-300">vindo</span>
                </div>

                <div class="hidden sm:block text-7xl text-center">
                    <div>
                        <span class="font-extrabold">Que bom</span>
                        {{ " " }}
                        <span class="font-semibold text-gray-300">ter</span>
                    </div>
                    <div>
                        <span class="font-extrabold">você</span>
                        {{ " " }}
                        <span class="font-semibold text-gray-300"
                            >de volta</span
                        >
                    </div>
                </div>

                <article>
                    <aside class="flex flex-col gap-5">
                        <div class="relative">
                            <label
                                for="inputEmail"
                                class="rounded-full absolute top-1 left-1 w-8 h-8 cursor-pointer bg-gray-200 flex items-center justify-center"
                            >
                                <img :src="icon" alt="img-icon-email" />
                            </label>
                            <input
                                type="email"
                                name="email"
                                id="inputEmail"
                                class="indent-10 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-full block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
                                placeholder="E-Mail"
                                required
                                maxlength="255"
                            />
                        </div>
                        <div class="relative">
                            <label
                                @click="togglePassword"
                                for="inputPassword"
                                class="absolute rounded-full top-1 left-1 w-8 h-8 cursor-pointer bg-gray-200 flex items-center justify-center"
                            >
                                <img
                                    :src="iconPassword"
                                    alt="img-icon-password"
                                />
                            </label>
                            <input
                                :type="showPassword ? 'text' : 'password'"
                                name="password"
                                id="inputPassword"
                                class="indent-10 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-full block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
                                placeholder="Password"
                                required
                                maxlength="255"
                            />
                        </div>
                    </aside>

                    <div class="pt-5">
                        <button
                            class="w-full py-3 bg-white rounded-full text-gray-500"
                            type="submit"
                        >
                            Entrar
                        </button>
                    </div>
                </article>

                <p class="errorsForm"></p>
            </div>
        </form>
    </main>
</template>
