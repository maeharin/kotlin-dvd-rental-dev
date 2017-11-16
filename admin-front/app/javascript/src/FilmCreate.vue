<template>
    <div>
        <h1>映画 新規作成</h1>

        <el-form ref="form" :model="form" :rules="rules" label-width="120px" size="mini">
            <el-form-item label="タイトル" prop="title">
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

            <el-form-item label="言語">
                <el-select v-model="form.languageId" filterable placeholder="選択してください">
                    <el-option
                        v-for="lang in languages"
                        :key="lang.id"
                        :label="lang.name"
                        :value="lang.id">
                        {{ lang.name }}
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="カテゴリ">
                <el-checkbox-group v-model="form.categoryIds">
                    <el-checkbox
                        v-for="category in categories"
                        :label="category.id"
                        :key="category.id">
                        {{category.name}}
                    </el-checkbox>
                </el-checkbox-group>
            </el-form-item>

            <el-form-item label="出版年">
                <el-input type="text" v-model.number="form.releaseYear" />年
            </el-form-item>

            <el-form-item label="再生時間">
                <el-input type="text" v-model.number="form.length" />分
            </el-form-item>

            <el-form-item label="レンタル料金">
                <el-input type="text" v-model.number="form.rentalRate" />$
            </el-form-item>

            <el-form-item label="原価">
                <el-input type="text" v-model.number="form.replacementCost" />$
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">作成</el-button>
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
            csrfToken: document.querySelector('meta[name="csrf-token"]')!.getAttribute('content'),
            form: {
                actorIds: [],
                categoryIds: [],
            },
            actors: [],
            isSearchActorLoading: false,
            languages: [],
            categories: [],
            rules: {
                title: [
                    { required: true, message: '必須項目です', trigger: 'blur' },
                ]
            }
        }
    },
    async mounted() {
        try {
            this.languages = (await axios.get("/ajax/masterdatas/languages")).data
            this.categories = (await axios.get("/ajax/masterdatas/categories")).data
        } catch (e) {
            alert(e)
        }
    },
    methods: {
        async onSubmit() {
            try {
                await new Promise((resolve, reject) => {
                    const elForm: any = this.$refs["form"]
                    elForm.validate((isValid: boolean) => isValid
                        ? resolve() 
                        : reject(new Error("入力エラーがあります")
                    ))
                })

                const res = await axios.post("/ajax/films", {
                    authenticity_token: this.csrfToken,
                    film: this.form
                })

                this.$router.push("/")
            } catch (e) {
                alert(e)
            }
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