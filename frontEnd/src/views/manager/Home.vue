<template>
  <div style="display: flex;padding: 0 15px">
    <div style="width: 50%">
      <div class="card_title">电影展示</div>
      <el-card></el-card>
      <el-card></el-card>
      <el-card></el-card>
      <el-card></el-card>
    </div>
    <div style="width: 50%">
      <div class="card_title">系统公告</div>
      <el-timeline>
        <el-timeline-item v-for="item in data.timeList" :key="item.id" :timestamp="item.time" placement="top">
          <el-card>
            <h4 style="font-size: 16px;font-weight: bold">{{ item.title }}</h4>
            <p style="color:#ccc">{{ item.content }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
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
