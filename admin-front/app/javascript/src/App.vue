<template>
    <el-container>
        <el-header>
            kotlin dvd rental 管理画面
        </el-header>

        <el-container>
            <el-aside>
                <el-menu
                    class="el-menu-vertical-demo"
                    router
                >
                    <el-menu-item index="/">
                        <i class="el-icon-menu"></i>
                        <span>映画</span>
                    </el-menu-item>
                    <el-menu-item index="/customers">
                        <i class="el-icon-setting"></i>
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