<template>
    <main class="w-[100%] h-[100dvh] bg-black mainForm">
        <form
            class="glassmorphism w-[100%] h-[100dvh] flex justify-center items-center text-white flex-col gap-10"
            method="post"
        >
            <div class="text-7xl">
                <span class="font-extrabold">Bem</span>
                {{ " " }}
                <span class="font-semibold text-gray-300">vindo</span>
            </div>
            <article>
                <div class="relative">
                    <label
                        for="inputEmail"
                        class="absolute top-pill left-1 w-8 h-8 cursor-pointer"
                    >
                        <img
                            src="https://fakeimg.pl/20/"
                            alt="img-icon-email"
                            class="rounded-full w-full h-full"
                        />
                    </label>
                    <input
                        type="email"
                        name="email"
                        id="inputEmail"
                        class="indent-8 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-full block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
                        placeholder="E-Mail"
                        required
                        maxlength="255"
                    />
                </div>
                <div class="relative">
                    <label
                        for="inputPassword"
                        class="absolute top-pill left-1 w-8 h-8 cursor-pointer"
                    >
                        <img
                            src="https://fakeimg.pl/20/"
                            alt="img-icon-password"
                            class="rounded-full w-full h-full"
                        />
                    </label>
                    <input
                        type="password"
                        name="password"
                        id="inputPassword"
                        class="indent-8 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-full block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
                        placeholder="Password"
                        required
                        maxlength="255"
                    />
                </div>
                <div class="pt-5">
                    <button
                        class="w-[100%] py-3 bg-white rounded-full text-gray-500"
                        type="submit"
                    >
                        Entrar
                    </button>
                </div>
            </article>
            <p class="errorsForm"></p>
        </form>
    </main>
</template>
