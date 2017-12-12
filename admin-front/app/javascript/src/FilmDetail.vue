<template>
    <div>
        <el-card v-if="film">
            <h1>{{ film.title }} ({{ film.releaseYear }})</h1>
            <table>
                <tr>
                    <th>説明</th>
                    <td>{{ film.description }}</td>
                </tr>
                <tr>
                    <th>言語</th>
                    <td>{{ film.language.name }}</td>
                </tr>
                <tr>
                    <th>レンタル料金</th>
                    <td>{{ film.rentalRate }}$</td>
                </tr>
                <tr>
                    <th>再生時間</th>
                    <td>{{ film.length }}分</td>
                </tr>
                <tr>
                    <th>カテゴリ</th>
                    <td>
                        <span v-for="category in film.categories" :key="category.id">
                            {{ category.name }}
                        </span>
                    </td>
                </tr>
                <tr>
                    <th>主演</th>
                    <td>
                        <span v-for="actor in film.actors" :key="actor.id">
                            {{ actor.firstName }}
                        </span>
                    </td>
                </tr>
            </table>
        </el-card>
    </div>
</template>

<script lang="ts">
import Vue from 'vue'
import { FilmResource } from '@kotlin-dvd-rental/staff-client'

export default Vue.extend({
    data() {
        return {
            id: this.$route.params.id,
            film: null as FilmResource | null
        }
    },
    async mounted() {
        try {
            const res = await this.$http.get(`/ajax/films/${this.id}`)
            this.film = res.data
        } catch (e) {
            alert(e)
        }
    },
})
</script>