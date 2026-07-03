<template>
  <div>
    <el-container>
      <el-heander style="background-color: #409EFF; width: 1700px; height: 70px">
        <div style="margin-top: 14px;float: left;">
          <span style="font-size: 30px; margin-left: 50px; color: white;">供暖系统后台</span>
        </div>
        <el-dropdown style="float: right;">
          <div>
            <span class="el-dropdown-link" style="color: white; font-size: 16px; margin-right: 40px; line-height: 70px; cursor: pointer">{{ user.account}}</span>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <div @click="logout()">退出登录</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-heander>
    </el-container>
    <el-container>
      <el-aside style="overflow: hidden; min-height: 870px; background-color: white;width: 150px">
        <el-menu :default-active="$route.path" router background-color="white " text-color=" #eeeee" active-text="#409EFF">
            <el-menu-item-group>
              <el-menu-item index="/House_fb" style="text-align: center">住户分布</el-menu-item>
              <el-menu-item index="/Heating_engineer" style="text-align: center">供暖师傅管理</el-menu-item>
              <el-menu-item index="/User" style="text-align: center">用户管理</el-menu-item>
              <el-menu-item index="/House_address" style="text-align: center">住房管理</el-menu-item>
              <el-menu-item index="/Pay_the_fees" style="text-align: center">缴费记录</el-menu-item>
              <el-menu-item index="/Advance_fee" style="text-align: center">预交费缴费记录</el-menu-item>
              <el-menu-item index="/Maintenance_record" style="text-align: center">维修记录</el-menu-item>
              <el-menu-item index="/Accessories" style="text-align: center">配件管理</el-menu-item>
              <el-menu-item index="/Temperature" style="text-align: center">温度控制</el-menu-item>
              <el-menu-item index="/Notice" style="text-align: center">公告管理</el-menu-item>
              <el-menu-item index="/Feedback" style="text-align: center">意见管理</el-menu-item>
              <el-menu-item index="/log" style="text-align: center">日志管理</el-menu-item>
            </el-menu-item-group>

        </el-menu>
      </el-aside>
      <el-main>

          <router-view/>

      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return{
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.first()
  },
  methods: {
    first() {
      if (this.user.role === "admin") {

      } else {
        this.$message.error('用户身份有误，请重新登录')
        this.$router.push("/AdminLogin")
      }
    },
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/AdminLogin");
    }
  }
}
</script>
<style>
.el-menu{
  border-right: none !important;
}
.rounded-rectangle {

  background-color: white;
  border: 1px solid #ccc;
  border-radius: 10px; /* 设置圆角半径 */
}
</style>