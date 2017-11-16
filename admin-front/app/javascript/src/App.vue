<template>
    <el-container>
        <el-header id="main">
            <h1>kotlin dvd rental 管理画面</h1>
        </el-header>

        <el-container>
            <el-aside width="150px">
                <el-menu router>
                    <el-menu-item index="/">
                        <span>映画</span>
                    </el-menu-item>
                    <el-menu-item index="/customers">
                        <span>顧客</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>

            <el-main>
                <router-view></router-view>
            </el-main>
         </el-container>
    </el-container>
</template>

<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
})
</script>