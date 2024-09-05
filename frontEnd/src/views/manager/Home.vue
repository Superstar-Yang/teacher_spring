<template>
  <div style="display: flex;padding: 0 15px;grid-gap: 20px">
    <div style="flex: 1;">
      <el-card>
        <div class="card_title">系统公告</div>
        <el-timeline>
          <el-timeline-item v-for="item in data.timeList" :key="item.id" :timestamp="item.time" placement="bottom">

            <h4 style="font-size: 16px;font-weight: bold">{{ item.title }}</h4>
            <p style="color:#ccc">{{ item.content }}</p>
          </el-timeline-item>
        </el-timeline>
      </el-card>
    </div>
    <div style="flex:1">
      <el-card>
        <div>项目标题：学生选课管理系统</div>
        <div>项目技术栈：springBoot3/vue3/elementPlus/mySql</div>
        <div>项目功能描述：xxxxx</div>
      </el-card>
    </div>
  </div>
</template>

<script setup>
import {reactive} from "vue";
import request from "@/utils/request";

const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}'),
  timeList: []
})
const load = () => {
  request('/notice/selectAll').then(res => {
    data.timeList = res?.data
  })
}
load()
</script>
<style lang="scss" scoped>
.card_title {
  margin: 20px;
  font-weight: bold;
  font-size: 20px
}
</style>
