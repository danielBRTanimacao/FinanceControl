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
        <p class="text-gray-500 font-bold">{{ data.title }}</p>
        <small class="uppercase">{{ data.category.name }}</small>
        <p>
            <span class="valueCondition">{{ data.value > 0 ? "+" : "-" }}</span>
            $
            <span class="valueTransaction">{{
                data.value > 0
                    ? data.value.toFixed(2)
                    : (data.value * -1).toFixed(2)
            }}</span>
        </p>
    </div>
</template>
