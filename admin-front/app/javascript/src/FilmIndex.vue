<template>
    <div>
        <h1>映画一覧</h1>

        <el-button
            size="mini"
            type="success"
            @click="onCreateButtonClicked"
        >
            新規作成
        </el-button>

        <el-table :data="films" style="width: 100%">
            <el-table-column label="id" width="80" prop="id">
            </el-table-column>

            <el-table-column label="タイトル" width="200">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.title }} ({{ scope.row.releaseYear }})</span>
                </template>
            </el-table-column>

            <el-table-column label="レンタル料金" width="120" prop="rentalRate">
            </el-table-column>

            <el-table-column label="言語" width="100" prop="language.name">
            </el-table-column>

            <el-table-column label="再生時間" width="100">
                <template slot-scope="scope">
                    {{ scope.row.length }}分
                </template>
            </el-table-column>

            <el-table-column label="カテゴリ" width="100" prop="actors">
                <template slot-scope="scope">
                    {{ scope.row.categories.map(c => c.name).join(",") }}
                </template>
            </el-table-column>

            <el-table-column>
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="onShowButtonClicked(scope.$index, scope.row)"
                    >
                        詳細
                    </el-button>
                    <el-button
                        size="mini"
                        type="primary"
                        @click="onEditButtonClicked(scope.$index, scope.row)"
                    >
                        編集
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script lang="ts">
import Vue from 'vue'
import { FilmResource } from '@kotlin-dvd-rental/staff-client'

export default Vue.extend({
    data() {
        return {
            films: [] as FilmResource[],
        }
    },
    async mounted() {
        try {
            const res = await this.$http.get("/ajax/films")
            this.films = res.data
        } catch (e) {
            alert(e)
        }
    },
    methods: {
        onCreateButtonClicked() {
            this.$router.push("/films/new")
        },
        onShowButtonClicked(index: number, row: any) {
            this.$router.push(`/films/${row.id}`)
        },
        onEditButtonClicked() {
            // 補完が効くか試してみる
            console.log(this.films.map(film => film.actors.map(actor => actor.firstName)).join(","))
        }
    }
})
</script>