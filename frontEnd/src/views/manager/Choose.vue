<template>
  <div>

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.name" style="width: 300px; margin-right: 10px"
                placeholder="请输入名称查询"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tableData" stripe>
        <el-table-column label="课程名称" prop="name"></el-table-column>
        <el-table-column label="授课教师" prop="teacherName"></el-table-column>
        <el-table-column label="选课学生" prop="studentName"></el-table-column>
        <el-table-column label="操作" align="center" width="160" v-if="data.user.role !== 'TEACHER'">
          <template #default="scope" v-if="data.user.role === 'ADMIN'">
            <el-button type="primary" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
          <template #default="scope">
            <el-button type="primary" @click="handleDelete(scope.row.id)">取消选课</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card" v-if="data.total > 0">
      <el-pagination background :page-sizes="[5,10,15,20]" layout=" sizes, prev, pager, next, jumper,->,total"
                     v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"
                     @change="currentChange"/>
    </div>

  </div>
</template>

<script setup>
import request from "@/utils/request";
import {reactive} from "vue";
import {ElMessageBox, ElMessage} from "element-plus";

const data = reactive({
  pageNum: 1,
  pageSize: 5,
  total: 0,
  form: {},
  tableData: [],
  name: null,
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
})

// 分页查询
const load = () => {
  request.get('/choose/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name,
      teacherId: data.user.role === 'TEACHER' ? data.user.id : null,
      studentId: data.user.role === 'STUDENT' ? data.user.id : null
    }
  }).then(res => {
    data.tableData = res.data?.list
    data.total = res.data?.total
  })
}

// 删除
const handleDelete = (id) => {
  ElMessageBox.confirm(data.user.role === 'ADMIN' ? '删除后数据无法恢复，您确定删除吗?' : '取消后就无法选取了,您确定取消吗？', data.user.role === 'ADMIN' ? '删除确认' : "取消确认", {type: 'warning'}).then(res => {
    request.delete('/choose/delete/' + id).then(res => {
      if (res.status === 200) {
        ElMessage.success(data.user.role === 'ADMIN' ? '操作成功' : '取消成功')
        load()
      } else {
        ElMessage.error(res.statusText)
      }
    })
  }).catch(err => {
  })
}

// 重置
const reset = () => {
  data.name = null
  load()
}

// 分页方法
const currentChange = (number, size) => {
  data.pageNum = number
  data.pageSize = size
  load()
}

load()
</script>
