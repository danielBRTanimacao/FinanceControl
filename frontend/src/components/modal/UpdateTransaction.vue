<script setup>
import axios from "axios";
import { getCookie } from "@/utils/authUtils";

const props = defineProps({
    show: Boolean,
    data: Object,
});
const emit = defineEmits(["close"]);
</script>

<script>
export default {
    data() {
        return {
            dataCategories: [],
        };
    },
    async created() {
        await this.fetchCategories();
    },
    methods: {
        async fetchCategories() {
            try {
                const response = await axios.get(
                    "http://127.0.0.1:8080/api/categories",
                    {
                        headers: {
                            Authorization: `Bearer ${getCookie("token")}`,
                        },
                    }
                );
                console.log(response);
                this.dataCategories = response.data;
            } catch (error) {
                console.log(error);
            }
        },
        async submitTransactionForm() {
            try {
                const response = await axios.put(
                    `http://127.0.0.1:8080/api/transaction/${this.data.id}`,
                    this.data,
                    {
                        headers: {
                            Authorization: `Bearer ${getCookie("token")}`,
                        },
                    }
                );

                window.location.reload();
            } catch (error) {
                console.log("Deu bo", error);
            }
        },
    },
};
</script>

<template>
    <section
        v-if="show"
        class="fixed inset-0 z-50 flex items-center justify-center bg-gray-500 glassmorphism bg-opacity-50"
    >
        <div
            class="mx-5 p-6 rounded-lg h-90 max-w-md w-full relative"
            style="background-color: #202140"
        >
            <button
                @click="$emit('close')"
                class="absolute top-2 right-2 text-gray-500 cursor-pointer"
            >
                ✖
            </button>
            <div>
                <form
                    @submit.prevent="submitTransactionForm"
                    method="post"
                    class="m-3 flex flex-col gap-2"
                >
                    <label for="titleTransaction">Nome da transação:</label>
                    <input
                        v-model="data.title"
                        class="rounded border border-gray-300 text-gray-900 text-sm block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
                        type="text"
                        name="title"
                        id="titleTransaction"
                        required
                        maxlength="255"
                        min="4"
                    />
                    <label for="valueTransaction">Valor:</label>
                    <input
                        v-model="data.value"
                        class="rounded border border-gray-300 text-gray-900 text-sm block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
                        type="number"
                        name="value"
                        id="valueTransaction"
                        required
                        step="0.01"
                    />
                    <label for="categoryOption">Categoria:</label>
                    <select v-model="data.category.id" name="categoryOption">
                        <option
                            v-for="categorie in dataCategories"
                            :value="categorie.id"
                        >
                            {{ categorie.name }}
                        </option>
                    </select>
                    <button
                        class="cursor-pointer bg-blue-500 hover:bg-blue-700 rounded py-2"
                        type="submit"
                    >
                        Adicionar
                    </button>
                </form>
            </div>
        </div>
    </section>
</template>
