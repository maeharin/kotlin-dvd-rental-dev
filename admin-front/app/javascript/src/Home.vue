<template>
<div>
   <el-button type="primary" @click="dialogVisible = !dialogVisible">Primary</el-button>
   <el-button type="primary" @click="hello">hello</el-button>

   <el-dialog
     title="Tips"
     :visible.sync="dialogVisible"
     width="30%"
   >
     <span>This is a message</span>
     <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisible = false">Cancel</el-button>
       <el-button type="primary" @click="dialogVisible = false">Confirm</el-button>
     </span>
   </el-dialog>
</div>
</template>

<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
    data() {
        return {
            dialogVisible: false    
        }
    },
    computed: {
        foo(): string {
            return "foo"
        }
    },
    methods: {
        hello() {
            console.log(this.foo)
        }
    }
})
</script>