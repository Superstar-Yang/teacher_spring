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
        <el-form-item label="学院名称" prop="collegeId">
          <el-select v-model="data.user.collegeId">
            <el-option
                v-for="item in data.collegeList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
            >

            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="data.user.code" autocomplete="off" :disabled="data.user.role === 'ADMIN'"/>
        </el-form-item>
        <el-form-item label="学分">
          <el-input v-model="data.user.score" autocomplete="off" :disabled="data.user.role === 'ADMIN'"/>
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
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  collegeList: []
})

const handleFileUpload = (file) => {
  data.user.avatar = file.data
}

const emit = defineEmits(["updateUser"])
// 把当前修改的用户信息存储到后台数据库
const save = () => {
  request.put('/student/update', {
    params: {
      ...data.user,
      collegeId: data.user.collegeId
    }
  }).then(res => {
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

const getCollegeData  =()=>{
  request.get('/college/selectAll').then(res=>{
    data.collegeList = res.data
  })
}
getCollegeData()

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
