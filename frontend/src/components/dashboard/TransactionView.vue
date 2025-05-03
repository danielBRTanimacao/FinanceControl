<script>
export default {
    props: {
        data: {
            type: Object,
            required: true,
        },
    },
};
</script>

<template>
    <div class="bg-gray-800 rounded-xl p-2 mt-1">
        <p class="text-gray-500 font-bold">{{ data.name }}</p>
        <small class="uppercase">{{ data.category }}</small>
        <p>
            <span class="valueCondition">-</span>
            $
            <span class="valueTransaction">8.00</span>
        </p>
    </div>
</template>
