<template>
  <div>

    <div class="card" style="margin-bottom: 5px;">
      <el-input v-model="data.name" style="width: 300px; margin-right: 10px"
                placeholder="请输入名称查询"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="info" style="margin: 0 10px" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <div style="margin-bottom: 10px" v-if="data.user.role === 'ADMIN'">
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </div>
      <el-table :data="data.tableData" stripe>
        <el-table-column label="名称" prop="name"/>
        <el-table-column label="内容" prop="content"/>
        <el-table-column label="最低学分" prop="score"/>
        <el-table-column label="授课教师" prop="teacherName"/>
        <el-table-column label="开课人数" prop="num"/>
        <el-table-column label="开课时间" prop="time"/>
        <el-table-column label="开课地点" prop="location"/>
        <el-table-column label="所属学院" prop="collegeName"/>
        <el-table-column label="已选人数" prop="alreadyNum"/>
        <el-table-column label="操作" align="center" width="160" v-if="data.user.role !== 'TEACHER'">
          <template #default="scope" v-if="data.user.role === 'ADMIN'">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
          <template #default="scope" v-if="data.user.role === 'STUDENT'">
            <el-button type="primary" @click="handleChoose(scope.row)" :disabled="scope.row.alreadyNum === scope.row.num">选课</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="card">
      <el-pagination background :page-sizes="[5,10,15,20]" layout=" sizes, prev, pager, next, jumper,->,total"
                     v-model:page-size="data.pageSize" v-model:current-page="data.pageNum" :total="data.total"
                     @change="currentChange"/>
    </div>

    <el-dialog title="课程信息" width="40%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="data.form" label-width="100px" style="padding-right: 50px" label-suffix=":">
        <el-form-item label="名称" prop="name">
          <el-input v-model="data.form.name" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input type="textarea" v-model="data.form.content" autocomplete="off"/>
        </el-form-item>
        '
        <el-form-item label="最低学分" prop="score">
          <el-input v-model="data.form.score" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="授课教师" prop="teacherName">
          <el-select v-model="data.form.teacherId" placeholder="请选择授课教师">
            <el-option v-for="item in data.teacherList" :key="item.id" :value="item.id" :label="item.name"/>
          </el-select>
        </el-form-item>
        <el-form-item label="上课人数" prop="num">
          <el-input v-model="data.form.num" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="上课时间" prop="time">
          <el-input v-model="data.form.time" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="上课地点" prop="location">
          <el-input v-model="data.form.location" autocomplete="off"/>
        </el-form-item>
        <el-form-item label="所属学院" prop="collegeName">
          <el-select v-model="data.form.collegeId" placeholder="请选择所属学院">
            <el-option v-for="item in data.collegeList" :key="item.id" :value="item.id" :label="item.name"/>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="data.formVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </span>
      </template>
    </el-dialog>

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
  formVisible: false,
  form: {},
  tableData: [],
  name: null,
  teacherList: [],
  collegeList: [],
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
})

// 分页查询
const load = () => {

  request.get('/course/selectPage', {
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name: data.name,
      teacherId: data.user.role === 'TEACHER' ? data.user.id : null
    }
  }).then(res => {
    data.tableData = res.data?.list
    data.total = res.data?.total
  })
}

// 新增
const handleAdd = () => {
  data.form = {}
  data.formVisible = true
}

// 编辑
const handleEdit = (row) => {
  data.form = JSON.parse(JSON.stringify(row))
  data.formVisible = true
}

// 新增保存
const add = () => {
  request.post('/course/add', data.form).then(res => {
    if (res.status === 200) {
      load()
      ElMessage.success('操作成功')
      data.formVisible = false
    } else {
      ElMessage.error(res.statusText)
    }
  })
}

// 编辑保存
const update = () => {
  request.put('/course/update', data.form).then(res => {
    if (res.status === 200) {
      load()
      ElMessage.success('操作成功')
      data.formVisible = false
    } else {
      ElMessage.error(res.statusText)
    }
  })
}

// 弹窗保存
const save = () => {
  // data.form有id就是更新，没有就是新增
  data.form.id ? update() : add()
}

// 删除
const handleDelete = (id) => {
  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', {type: 'warning'}).then(res => {
    request.delete('/course/delete/' + id).then(res => {
      if (res.status === 200) {
        load()
        ElMessage.success('操作成功')
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

const getTeacherList = () => {
  request.get('/teacher/selectAll').then(res => data.teacherList = res.data)
}

const getCollegeList = () => {
  request.get('/college/selectAll').then(res => data.collegeList = res.data)
}

const handleChoose = (row)=>{
  let chooseData = JSON.parse(JSON.stringify(row))
  chooseData.studentId = data.user.id
  request.post('/choose/add', chooseData).then(res => {
    if (res.status === 200) {
      ElMessage.success('恭喜你,选课成功！')
      load()
    }else{
      ElMessage.error(res.statusText)
    }
  })
}

load()
getCollegeList()
getTeacherList()
</script>
