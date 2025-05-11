<script setup>
import { getCookie } from "@/utils/authUtils";
import axios from "axios";

const props = defineProps({ show: Boolean });
const emit = defineEmits(["close"]);
</script>

<script>
export default {
    data() {
        return {
            dataCategory: {
                name: "",
            },
        };
    },
    methods: {
        async submitTransactionForm() {
            try {
                const response = await axios.post(
                    "http://127.0.0.1:8080/api/categories",
                    this.dataCategory,
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
            class="mx-5 p-6 rounded-lg h-90 max-w-md w-full relative flex justify-center items-center"
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
                    <label for="nameCategory">Nome da transação:</label>
                    <input
                        v-model="dataCategory.name"
                        class="rounded border border-gray-300 text-gray-900 text-sm block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
                        type="text"
                        name="name"
                        id="nameCategory"
                        required
                        maxlength="255"
                        min="4"
                    />

                    <button
                        class="cursor-pointer bg-blue-500 hover:bg-blue-700 rounded py-2"
                        type="submit"
                    >
                        Criar
                    </button>
                </form>
            </div>
        </div>
    </section>
</template>
