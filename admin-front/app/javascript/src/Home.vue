<template>
<div>
    <el-button type="primary" @click="dialogVisible = !dialogVisible">Primary</el-button>

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

<script>
export default {
    data() {
        return {
            dialogVisible: false    
        }
    }
}
</script>