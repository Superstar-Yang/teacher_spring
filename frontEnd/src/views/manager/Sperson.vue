<template>
  <div style="width: 50%">
    <div class="card" style="padding: 30px">
      <el-form :model="data.user" label-width="100px" style="padding-right: 50px">
        <div style="margin: 20px 0; text-align: center">
          <el-upload :show-file-list="false" class="avatar-uploader" :action="uploadUrl" :on-success="handleFileUpload">
            <img v-if="data.user.avatar" :src="data.user.avatar" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon">
              <Plus/>
            </el-icon>
          </el-upload>
        </div>
        <el-form-item label="账号">
          <el-input disabled v-model="data.user.username" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="data.user.name" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="data.user.sex">
            <el-radio value="男" size="small">男</el-radio>
            <el-radio value="女" size="small">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="学院名称" prop="collegeId">
          <el-input v-model="data.user.collegeName" :disabled="data.user.role !== 'ADMIN'" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="data.user.code" autocomplete="off" :disabled="data.user.role !== 'ADMIN'"/>
        </el-form-item>
        <el-form-item label="学分">
          <el-input v-model="data.user.score" autocomplete="off" :disabled="data.user.role !== 'ADMIN'"/>
        </el-form-item>
        <div style="text-align: center">
          <el-button type="primary" @click="save">保存</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue"
import request from "@/utils/request";
import {ElMessage} from "element-plus";

// 文件上传的接口地址
const uploadUrl = import.meta.env.VITE_BASE_URL + '/files/upload'

const data = reactive({
  user: {},
})

const handleFileUpload = (file) => {
  data.user.avatar = file.data
}

const emit = defineEmits(["updateUser"])
// 把当前修改的用户信息存储到后台数据库
const save = () => {
  request.put('/student/update', data.user).then(res => {
    if (res.status === 200) {
      ElMessage.success('更新成功')
      //把更新后的用户信息存储到缓存
      localStorage.setItem('system-user', JSON.stringify(data.user))
      emit('updateUser')
    } else {
      ElMessage.error(res.statusText)
    }
  })
}
const loadStudent = () => {
  let user = JSON.parse(localStorage.getItem('system-user') || '{}')
  request.get('/student/selectById/' + user.id).then(res => {
    if(res.status === 200) {
      data.user = res.data
      localStorage.setItem('system-user', JSON.stringify(res.data))
    } else{
      ElMessage.error(res.statusText)
    }
  })
}
loadStudent()

</script>

<style scoped>
.avatar-uploader .avatar {
  width: 120px;
  height: 120px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  text-align: center;
}
</style>
