<template>
    <div>
        <h1>映画 新規作成</h1>

        <el-form ref="form" :model="form" label-width="120px" size="mini">
            <el-form-item label="タイトル">
                <el-input v-model="form.title"></el-input>
            </el-form-item>

            <el-form-item label="説明">
                <el-input v-model="form.description"></el-input>
            </el-form-item>

            <el-form-item label="出演者">
                  <el-select
                    v-model="form.actorIds"
                    multiple
                    filterable
                    remote
                    :reserve-keyword="false"
                    placeholder="名前で検索してください"
                    :remote-method="searchActors"
                    :loading="isSearchActorLoading">
                        <el-option
                            v-for="actor in actors"
                            :key="actor.id"
                            :label="actor.fullName"
                            :value="actor.id">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="出版年">
                <el-date-picker
                    v-model="form.releaseYear"
                    type="year"
                    placeholder="出版年を選択してください">
                </el-date-picker>
            </el-form-item>

            <el-form-item label="レンタル料金">
                <el-input type="text" v-model.number="form.rentalRate" />$
            </el-form-item>

            <el-form-item label="原価">
                <el-input type="text" v-model.number="form.replacementCost" />$
            </el-form-item>

            <el-form-item label="再生時間">
                <el-input type="text" v-model.number="form.length" />分
            </el-form-item>

            <el-form-item label="言語">
                <el-checkbox-group v-model="form.languageId">
                </el-checkbox-group>
            </el-form-item>

            <el-form-item label="カテゴリ">
                <el-checkbox-group v-model="form.categoryIds">
                    <el-checkbox label="Online activities" name="type"></el-checkbox>
                    <el-checkbox label="Promotion activities" name="type"></el-checkbox>
                    <el-checkbox label="Offline activities" name="type"></el-checkbox>
                    <el-checkbox label="Simple brand exposure" name="type"></el-checkbox>
                </el-checkbox-group>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">Create</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script lang="ts">
import Vue from 'vue'
import axios from 'axios'

export default Vue.extend({
    data() {
        return {
            form: {
                actorIds: []
            },
            actors: [],
            isSearchActorLoading: false,
        }
    },
    methods: {
        async onSubmit() {
            console.log(this.form)
            //try {
            //    const res = await axios.post("/ajax/films")
            //    this.$router.push("/")
            //} catch (e) {
            //    alert(e)
            //}
        },
        async searchActors(query: string) {
            if (query == "") { return }

            try {
                this.isSearchActorLoading = true
                const res = await axios.get("/ajax/actors/search", {
                    params: {query: query}
                 })
                this.actors = res.data
                this.isSearchActorLoading = false
            } catch (e) {
                this.isSearchActorLoading = false
                alert(e)
            }
        }
    }
})
</script>