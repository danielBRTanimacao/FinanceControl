<template>
    <main class="m-7 border border-2 border-gray-700 rounded-xl h-fit">
        <header class="mx-3 py-5 flex justify-between items-center">
            <a href="/" class="uppercase font-bold text-2xl">Dashboard</a>
            <a href="#">
                <img class="rounded-full" src="https://fakeimg.pl/30/" alt="" />
            </a>
        </header>
        <section class="mx-3 py-7">
            <article class="pb-7">
                <select class="text-gray-600" name="typeEarns" id="userEarns">
                    <option value="earns">Dados ganhos</option>
                    <option value="spend">Dados gastos</option>
                </select>
                <p class="text-3xl">$<span class="typeValue">1,299.00</span></p>
            </article>
            <aside>
                <div class="flex justify-between text-gray-600 pb-3">
                    <p>Transações</p>
                    <a href="#all">Ver todas</a>
                </div>
                <div>
                    <div class="bg-gray-800 rounded-xl p-2 mt-1">
                        <p class="text-gray-500 font-bold">Nome da transação</p>
                        <small>categoria</small>
                        <p><span>-</span>$<span>0.00</span></p>
                    </div>
                    <div class="bg-gray-800 rounded-xl p-2 mt-1">
                        <p class="text-gray-500 font-bold">Nome da transação</p>
                        <small>categoria</small>
                        <p><span>-</span>$<span>0.00</span></p>
                    </div>
                    <div class="bg-gray-800 rounded-xl p-2 mt-1">
                        <p class="text-gray-500 font-bold">Nome da transação</p>
                        <small>categoria</small>
                        <p><span>-</span>$<span>0.00</span></p>
                    </div>
                </div>
            </aside>
            <div class="flex justify-evenly pt-5">
                <a
                    href="#addTransaction"
                    class="flex flex-col items-center justify-center gap-2"
                >
                    <div
                        class="grid place-items-center bg-purple-500 text-center rounded-full size-12 text-3xl"
                    >
                        +
                    </div>
                    <small>Add transaction</small>
                </a>
                <a
                    href="#addTransaction"
                    class="flex flex-col items-center justify-center gap-2"
                >
                    <div
                        class="grid place-items-center bg-gray-800 text-center rounded-full size-12 text-3xl"
                    >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-calendar"
                            viewBox="0 0 16 16"
                        >
                            <path
                                d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"
                            />
                        </svg>
                    </div>
                    <small>Gráfico</small>
                </a>
            </div>
        </section>
    </main>
</template>
