<script>
import TransactionView from "@/components/dashboard/TransactionView.vue";
import emptImage from "../../assets/imgs/svgs/empty.svg";
import axios from "axios";

async function fetchTransactions() {
    try {
        const response = await axios.get(
            "http://127.0.0.1:8080/api/transactions"
        );
        console.log(response);
    } catch (e) {
        console.log("Erro " + e);
    }
}

fetchTransactions();

export default {
    data() {
        return {
            emptyImg: emptImage,
            testIsNull: false,
        };
    },
    components: {
        TransactionView,
    },
};
</script>

<template>
    <main class="m-7 border border-2 border-gray-700 rounded-xl h-fit">
        <header class="mx-3 py-5 flex justify-between items-center">
            <a href="/" class="uppercase font-bold text-2xl">Dashboard</a>
            <a href="#">
                <img
                    class="rounded-full"
                    src="https://fakeimg.pl/35/"
                    alt="icon-user"
                />
            </a>
        </header>
        <section class="mx-3 py-7">
            <article class="pb-7">
                <select class="text-gray-600" name="typeEarns" id="userEarns">
                    <option value="earns">Dados ganhos</option>
                    <option value="spend">Dados gastos</option>
                </select>
                <p class="text-3xl">$<span class="typeValue">1,294.50</span></p>
            </article>
            <aside>
                <div class="flex justify-between text-gray-600 pb-3">
                    <p>Transações</p>
                    <a href="#all">Ver todas</a>
                </div>
                <div
                    v-if="testIsNull"
                    class="flex flex-col justify-center items-center"
                    id="emptyAlert"
                >
                    <img
                        :src="emptyImg"
                        class="w-[60%]"
                        alt="empty-transactions-image"
                    />
                    <h3 class="text-gray-500">
                        Você não possui nenhuma transação
                    </h3>
                    <h2 class="text-xl text-gray-400 uppercase">
                        ADICIONE UMA!
                    </h2>
                </div>
                <div v-else class="allTransactions">
                    <TransactionView />
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
                    <small>Adicionar transação</small>
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
