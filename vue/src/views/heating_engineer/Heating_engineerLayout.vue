<template>
  <el-container style="background-color: darkslategray">
    <el-aside style="overflow: hidden; min-height: 700px; width: 212px;  background-color: darkslategray">
      <div style="height: 60px">
        <img :src="user.img" alt="" style="width: 209px; border-radius: 50%; ">
      </div>
      <div style="margin-top: 160px">
        <span>{{ user.name }}</span>
      </div>
      <el-menu :default-active="$route.path" router background-color="darkslategray" text-color="white" active-text="#409EFF" style="margin-top: 20px">
        <el-menu-item index="/Heating_engineerLayout/Territory">负责区域</el-menu-item>
        <el-menu-item index="/Heating_engineerLayout/Maintenance_recordVue">维修记录</el-menu-item>
        <el-menu-item index="/Heating_engineerLayout/AccessoriesVue">配件库存</el-menu-item>
        <el-menu-item index="/Heating_engineerLayout/Heating_engineerMessage">个人中心</el-menu-item>
        <el-menu-item @click="back()">退出</el-menu-item>
      </el-menu>
    </el-aside>
    <el-main style="margin-right: 100px">
      <div class="rounded-rectangle" >
        <scroll-announcement :noticeList="announcements"></scroll-announcement>
        <router-view  @transmit="getMessage"></router-view>
      </div>
    </el-main>
  </el-container>
</template>
<script>
import request from "@/utils/request";
import ScrollAnnouncement from "@/views/user/ScrollAnnouncement.vue";
export default {
  components: {ScrollAnnouncement},

  data() {
    return {
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      announcements: [

      ]
    }
  },

  created() {
    this.first();
    this.getMessage();
  },
  mounted() {
    this.startMove()
  },

  methods: {
    startMove () {
      // console.log(this.textArr[0].tag)
      let state = "已公布"
      request.get("/notice/findState/" + state).then(res => {
        if (res.code === '0'){
          console.log(res.data)
          this.announcements = res.data
        }

      })

    },
    first() {
      console.log(this.user.name)
      if (this.user.role === "heating_engineer") {
      } else {
        this.$message.error('用户身份有误，请重新登录')
        this.$router.push("/Heating_engineerLogin")
      }
    },
    getMessage(data) {
      if (data === undefined) {

      } else {
        // console.log(data)
        this.user.img = data
        // console.log(this.user.img)
      }
    },
    back() {
      localStorage.removeItem("user");
      this.$router.push('/Heating_engineerLogin')
    }
  }
}

</script>
<style scoped>
.el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        //line-height: 500px;
      }

.el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        //line-height: 300px;
      }

</style>