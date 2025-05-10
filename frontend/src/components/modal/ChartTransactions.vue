<script setup>
import { ref, watch } from "vue";
import Chart from "chart.js/auto";
import axios from "axios";

const props = defineProps({ show: Boolean });
const emit = defineEmits(["close"]);

const chartRef = ref(null);
let chartInstance = null;

const fetchDataAndRenderChart = async () => {
    try {
        const token = localStorage.getItem("token");

        const response = await axios.get(
            "http://127.0.0.1:8080/api/transactions",
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );

        const data = response.data;

        // Agrupar valores por earnedDate
        const groupedByDate = {};
        data.forEach((t) => {
            const date = t.earnedDate || "Sem data";
            groupedByDate[date] =
                (groupedByDate[date] || 0) + parseFloat(t.value);
        });

        const labels = Object.keys(groupedByDate);
        const values = Object.values(groupedByDate);

        if (chartInstance) chartInstance.destroy();

        chartInstance = new Chart(chartRef.value, {
            type: "line", // ou "bar"
            data: {
                labels,
                datasets: [
                    {
                        label: "Ganhos por Data",
                        data: values,
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
    } catch (error) {
        console.error("Erro ao buscar transações:", error);
    }
};

watch(
    () => props.show,
    (newVal) => {
        if (newVal) {
            setTimeout(() => fetchDataAndRenderChart(), 100);
        }
    }
);
</script>

<template>
    <section
        v-if="show"
        class="fixed inset-0 z-50 flex items-center justify-center bg-gray-500 glassmorphism bg-opacity-50"
    >
        <div
            class="mx-5 p-6 rounded-lg h-90 max-w-md w-75 relative"
            style="background-color: #202140"
        >
            <button
                @click="$emit('close')"
                class="absolute top-2 right-2 text-gray-300 hover:text-white"
            >
                ✖
            </button>
            <div>
                <h1 class="text-white text-xl mb-4">
                    Gráfico de Ganhos por Data
                </h1>
                <canvas ref="chartRef" width="400" height="300"></canvas>
            </div>
        </div>
    </section>
</template>
