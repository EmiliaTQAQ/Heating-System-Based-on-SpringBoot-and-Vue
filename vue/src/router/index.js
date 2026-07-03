import Vue from 'vue'
import VueRouter from 'vue-router'
import AdminLogin from "@/views/admin/AdminLogin.vue";
import LogView from "@/views/admin/LogView.vue";
import UserLogin from "@/views/user/UserLogin.vue";
import Heating_engineerLogin from "@/views/heating_engineer/Heating_engineerLogin.vue";
import AdminLayout from "@/views/admin/AdminLayout.vue";
import Heating_engineer from "@/views/admin/Heating_engineer.vue";
import User from "@/views/admin/User.vue";
import Notice from "@/views/admin/Notice.vue";
import Feedback from "@/views/admin/Feedback.vue";
import Heating_engineerLayout from "@/views/heating_engineer/Heating_engineerLayout.vue";
import Heating_engineerMessage from "@/views/heating_engineer/Heating_engineerMessage.vue";
import Territory from "@/views/heating_engineer/Territory.vue";
import House_fb from "@/views/admin/House_fb.vue";
import Pay_the_fees from "@/views/admin/Pay_the_fees.vue";
import Maintenance_record from "@/views/admin/Maintenance_record.vue";
import House_address from "@/views/admin/House_address.vue";
import Accessories from "@/views/admin/Accessories.vue";
import AccessoriesVue from "@/views/heating_engineer/AccessoriesVue.vue";
import UserLayout from "@/views/user/UserLayout.vue";
import UserMessage from "@/views/user/UserMessage.vue";
import Maintenance_recordVue from "@/views/heating_engineer/Maintenance_recordVue.vue";
import Maintenance from "@/views/user/Maintenance.vue";
import home from "@/views/user/home.vue";
import F_history from "@/views/user/F_history.vue";
import test from "@/views/user/ScrollAnnouncement.vue";
import Temperature from "@/views/admin/Temperature.vue";
import Advance_fee from "@/views/admin/Advance_fee.vue";


Vue.use(VueRouter)

const routes = [
  {
    path: '/AdminLogin',
    name: 'AdminLogin',
    component: AdminLogin,
  },

  {
    path: '/',
    name: 'AdminLayout',
    component: AdminLayout,
    children: [
      {
        path: '/Heating_engineer',
        name: 'Heating_engineer',
        component: Heating_engineer
      },
      {
        path: '/User',
        name: 'User',
        component: User
      },
      {
        path: '/log',
        name: 'log',
        component: LogView
      },
      {
        path: 'Notice',
        name: 'Notice',
        component: Notice
      },
      {
        path: 'Feedback',
        name: 'Feedback',
        component: Feedback
      },
      {
        path: 'House_fb',
        name: 'House_fb',
        component: House_fb
      },
      {
        path: 'Pay_the_fees',
        name: 'Pay_the_fees',
        component: Pay_the_fees
      },
      {
        path: 'Advance_fee',
        name: 'Advance_fee',
        component: Advance_fee
      },
      {
        path: 'Maintenance_record',
        name: 'Maintenance_record',
        component: Maintenance_record
      },
      {
        path: 'House_address',
        name: 'House_address',
        component: House_address
      },
      {
        path: 'Accessories',
        name: 'Accessories',
        component: Accessories
      },
      {
        path: 'Temperature',
        name: 'Temperature',
        component: Temperature
      },

    ]
  },

  {
    path: '/Heating_engineerLayout',
    name: 'Heating_engineerLayout',
    component: Heating_engineerLayout,
    children: [
      {
        path: '/Heating_engineerLayout/Heating_engineerMessage',
        name: 'Heating_engineerMessage',
        component: Heating_engineerMessage
      },
      {
        path: '/Heating_engineerLayout/Territory',
        name: 'Territory',
        component: Territory
      },
      {
        path: '/Heating_engineerLayout/AccessoriesVue',
        name: 'AccessoriesVue',
        component: AccessoriesVue
      },
      {
        path: '/Heating_engineerLayout/Maintenance_recordVue',
        name: 'Maintenance_recordVue',
        component: Maintenance_recordVue
      },

    ]
  },

  {
    path: '/UserLayout',
    name: 'UserLayout',
    component: UserLayout,
    children: [
      {
        path: '/UserLayout/Heating_engineerMessage',
        name: 'Heating_engineerMessage',
        component: Heating_engineerMessage
      },
      {
        path: '/UserLayout/UserMessage',
        name: 'UserMessage',
        component: UserMessage
      },
      {
        path: '/UserLayout/Maintenance',
        name: 'Maintenance',
        component: Maintenance
      },
      {
        path: '/UserLayout/home',
        name: 'home',
        component: home
      },
      {
        path: '/UserLayout/F_history',
        name: 'F_history',
        component: F_history
      },
      {
        path: '/UserLayout/test',
        name: 'test',
        component: test
      },
    ]
  },
  {
    path: '/UserLogin',
    name: 'UserLogin',
    component: UserLogin
  },
  {
    path: '/Heating_engineerLogin',
    name: 'Heating_engineerLogin',
    component: Heating_engineerLogin
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
