<template>
    <span>{{scoring}}</span>
</template>

<script>
    const api = 'http://localhost:9999';

    export default {
        name: "Scoring",
        data: function () {
            return {
                scoring: 'NONE'
            }
        },
        methods: {
            fetchData() {
                this.$http.get(api + '/financialneeds/scorings/javorex')
                    .then((result) => {
                        this.survey = result.data;
                    });
            }
        }
    }
</script>

<style scoped>

</style>
