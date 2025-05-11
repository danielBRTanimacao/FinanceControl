<script>
import TransactionView from "@/components/dashboard/TransactionView.vue";
import CreateTransaction from "@/components/modal/CreateTransaction.vue";
import ChartTransactions from "@/components/modal/ChartTransactions.vue";
import emptImage from "../../assets/imgs/svgs/empty.svg";
import axios from "axios";
import Chart from "chart.js/auto";
import { getCookie } from "@/utils/authUtils";

let chartInstance = null;

export default {
    data() {
        return {
            emptyImg: emptImage,
            dataTransactions: [],
            haveTransaction: false,
            showModal: false,
            showChartModal: false,
            filterType: "earns",
        };
    },
    components: {
        TransactionView,
        CreateTransaction,
        ChartTransactions,
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
                if (this.haveTransaction) {
                    this.renderChart();
                }
            } catch (e) {
                console.log("Erro " + e);
                this.haveTransaction = false;
            }
        },

        renderChart() {
            if (chartInstance) {
                chartInstance.destroy();
            }

            const ctx = this.$refs.chartRef;
            if (!ctx) return;

            // Agrupar por data e somar os valores
            const grouped = {};
            this.dataTransactions.forEach((t) => {
                const date = t.earnedDate;
                if (!grouped[date]) grouped[date] = 0;
                grouped[date] += parseFloat(t.value);
            });

            const labels = Object.keys(grouped);
            const data = Object.values(grouped);

            chartInstance = new Chart(ctx, {
                type: "line",
                data: {
                    labels,
                    datasets: [
                        {
                            label: "Ganhos por Data",
                            data,
                            fill: true,
                            borderColor: "#4F46E5",
                            backgroundColor: "rgba(79, 70, 229, 0.2)",
                            tension: 0.3,
                        },
                    ],
                },
                options: {
                    responsive: true,
                    scales: {
                        x: { title: { display: true, text: "Data" } },
                        y: { title: { display: true, text: "Valor (R$)" } },
                    },
                },
            });
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
    <main
        class="m-7 border border-2 border-gray-700 rounded-xl h-[100dvh] md:h-full"
    >
        <header class="mx-3 py-5 flex justify-between items-center">
            <a href="/" class="uppercase font-bold text-2xl">Dashboard</a>
            <a href="#">
                <img
                    class="rounded-full border border-2"
                    src="https://randomuser.me/api/portraits/men/3.jpg"
                    width="40"
                    alt="icon-user"
                />
            </a>
        </header>
        <section
            class="mx-3 py-7 h-[90dvh] md:h-full flex flex-col justify-between md:gap-4"
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

                <section class="hidden md:block mx-3 py-7">
                    <div v-if="haveTransaction">
                        <canvas
                            ref="chartRef"
                            width="400"
                            height="300"
                        ></canvas>
                    </div>
                    <div v-else class="text-center text-gray-500 py-10">
                        <p class="text-lg">
                            Ainda não coletamos informações para o seu gráfico.
                        </p>
                    </div>
                </section>

                <aside>
                    <div class="flex justify-between text-gray-600 py-3">
                        <p>Transações</p>
                        <a href="#all">Ver todas</a>
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
                        class="h-[50dvh] overflow-auto"
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
                    @click.prevent="showChartModal = true"
                    href="#addTransaction"
                    class="md:hidden flex flex-col items-center justify-center gap-2"
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
    <ChartTransactions :show="showChartModal" @close="showChartModal = false" />
</template>
