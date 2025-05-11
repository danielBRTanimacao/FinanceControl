<script>
import TransactionView from "@/components/dashboard/TransactionView.vue";
import CreateTransaction from "@/components/modal/CreateTransaction.vue";
import emptImage from "../../assets/imgs/svgs/empty.svg";
import axios from "axios";
import { getCookie } from "@/utils/authUtils";

export default {
    data() {
        return {
            emptyImg: emptImage,
            dataTransactions: [],
            haveTransaction: false,
            showModal: false,
            showFilters: false,
            filterType: "earns",
        };
    },
    components: {
        TransactionView,
        CreateTransaction,
    },
    mounted() {
        this.fetchTransactions();
    },
    methods: {
        async fetchTransactions() {
            try {
                const response = await axios.get(
                    "http://127.0.0.1:8080/api/dashboard?max=20",
                    {
                        headers: {
                            Authorization: `Bearer ${getCookie("token")}`,
                        },
                    }
                );
                this.dataTransactions = response.data;
                this.haveTransaction = this.dataTransactions.length > 0;
            } catch (e) {
                console.log("Erro " + e);
                this.haveTransaction = false;
            }
        },
    },
    computed: {
        totalFilteredValue() {
            if (!this.dataTransactions.length) return 0;

            const filtered = this.dataTransactions.filter((t) => {
                return this.filterType === "earns" ? t.value > 0 : t.value < 0;
            });

            const total = filtered.reduce(
                (sum, t) => sum + parseFloat(t.value),
                0
            );

            return Math.abs(total).toFixed(2);
        },
    },
};
</script>

<template>
    <main class="m-4 h-[100dvh] md:h-full">
        <header class="mx-3 py-5 flex justify-between items-center">
            <a href="/" class="uppercase font-bold hover:text-gray-300 text-2xl"
                >Dashboard</a
            >
            <div class="flex items-center gap-5">
                <div
                    class="hidden md:flex items-center border-b border-gray-400 hover:border-gray-100"
                >
                    <label for="searchTransaction" class="text-gray-400">
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            width="16"
                            height="16"
                            fill="currentColor"
                            class="bi bi-search"
                            viewBox="0 0 16 16"
                        >
                            <path
                                d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"
                            />
                        </svg>
                    </label>
                    <input
                        placeholder="procurar transação..."
                        class="py-2 px-4 outline-0"
                        type="search"
                        name="q"
                        id="searchTransaction"
                    />
                </div>
                <a
                    @click.prevent="showModal = true"
                    href="#addTransaction"
                    class="hidden md:flex gap-1 items-center bg-purple-500 hover:bg-purple-800 rounded-full text-center py-2 px-3"
                >
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-patch-plus-fill"
                        viewBox="0 0 16 16"
                    >
                        <path
                            d="M10.067.87a2.89 2.89 0 0 0-4.134 0l-.622.638-.89-.011a2.89 2.89 0 0 0-2.924 2.924l.01.89-.636.622a2.89 2.89 0 0 0 0 4.134l.637.622-.011.89a2.89 2.89 0 0 0 2.924 2.924l.89-.01.622.636a2.89 2.89 0 0 0 4.134 0l.622-.637.89.011a2.89 2.89 0 0 0 2.924-2.924l-.01-.89.636-.622a2.89 2.89 0 0 0 0-4.134l-.637-.622.011-.89a2.89 2.89 0 0 0-2.924-2.924l-.89.01zM8.5 6v1.5H10a.5.5 0 0 1 0 1H8.5V10a.5.5 0 0 1-1 0V8.5H6a.5.5 0 0 1 0-1h1.5V6a.5.5 0 0 1 1 0"
                        />
                    </svg>
                    <small>Adicionar</small>
                </a>

                <a
                    href="/graficos"
                    class="hidden md:flex gap-1 items-center bg-gray-800 hover:bg-gray-700 rounded-full text-center py-2 px-3"
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
                    <small>Gráfico</small>
                </a>
                <a href="#">
                    <img
                        class="rounded-full border border-2"
                        src="https://randomuser.me/api/portraits/men/3.jpg"
                        width="40"
                        alt="icon-user"
                    />
                </a>
            </div>
        </header>
        <section
            class="mx-3 py-5 h-[90dvh] md:h-full flex flex-col justify-between md:gap-4"
        >
            <div>
                <article>
                    <select
                        v-model="filterType"
                        class="text-gray-600"
                        name="typeEarns"
                        id="userEarns"
                    >
                        <option value="earns">Dados ganhos</option>
                        <option value="spend">Dados gastos</option>
                    </select>
                    <p class="text-3xl">
                        $<span class="typeValue">{{ totalFilteredValue }}</span>
                    </p>
                </article>

                <aside>
                    <div class="flex justify-between items-center">
                        <p class="text-gray-600 pt-3">Transações</p>
                        <button
                            @click.prevent="showFilters = !showFilters"
                            class="cursor-pointer text-gray-600 hover:text-gray-400"
                        >
                            filtro
                        </button>
                    </div>
                    <div :class="showFilters ? `block` : `hidden`">
                        <div>
                            <input
                                type="radio"
                                id="mostRecentDate"
                                name="filter_chosed"
                                value=""
                            />
                            <!--Adicionar valor aqui-->
                            <label for="mostRecentDate">Data recente</label>
                            <br />
                            <input
                                type="radio"
                                id="pastDate"
                                name="filter_chosed"
                                value=""
                            />
                            <label for="pastDate">Data Passada</label><br />
                        </div>
                    </div>

                    <div
                        v-if="!haveTransaction"
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

                    <div
                        v-else
                        id="showTransactions"
                        class="h-[60dvh] overflow-auto"
                    >
                        <TransactionView
                            v-for="(transaction, index) in dataTransactions"
                            :key="index"
                            :data="transaction"
                        />
                    </div>
                </aside>
            </div>

            <div class="flex justify-evenly">
                <a
                    @click.prevent="showModal = true"
                    href="#addTransaction"
                    class="flex md:hidden flex-col hover:bg-purple-800 items-center justify-center gap-2"
                >
                    <div
                        class="grid place-items-center bg-purple-500 text-center rounded-full size-12 text-3xl"
                    >
                        +
                    </div>
                    <small>Adicionar transação</small>
                </a>

                <a
                    href="/graficos"
                    class="flex md:hidden flex-col items-center justify-center gap-2 hover:bg-gray-700"
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

    <CreateTransaction :show="showModal" @close="showModal = false" />
</template>
