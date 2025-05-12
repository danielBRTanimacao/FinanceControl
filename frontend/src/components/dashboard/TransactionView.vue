<script>
import UpdateTransaction from "../modal/UpdateTransaction.vue";

export default {
    props: {
        data: {
            type: Object,
            required: true,
        },
    },
    components: {
        UpdateTransaction,
    },
    data() {
        return {
            showModal: false,
        };
    },
};
</script>

<template>
    <div
        class="bg-gray-800 rounded-xl p-2 mt-1 flex items-center justify-between"
    >
        <div>
            <p class="text-gray-500 font-bold">{{ data.title }}</p>
            <small class="uppercase">{{ data.category.name }}</small>
            <p>
                <span class="valueCondition">{{
                    data.value > 0 ? "+" : "-"
                }}</span>
                $
                <span class="valueTransaction">{{
                    data.value > 0
                        ? data.value.toFixed(2)
                        : (data.value * -1).toFixed(2)
                }}</span>
            </p>
        </div>
        <div class="me-2">
            <button
                @click="showModal = true"
                class="cursor-pointer text-gray-400 hover:text-gray-200"
            >
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="20"
                    height="20"
                    fill="currentColor"
                    class="bi bi-pencil-square"
                    viewBox="0 0 16 16"
                >
                    <path
                        d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"
                    />
                    <path
                        fill-rule="evenodd"
                        d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"
                    />
                </svg>
            </button>
        </div>
    </div>
    <UpdateTransaction
        :show="showModal"
        :data="data"
        @close="showModal = false"
    />
</template>
