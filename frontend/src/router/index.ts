import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    alias: "/punchclock",
    name: "All punchclocks",
    component: () => import("../components/EntryList.vue"),
  },
  // {
  //     path: "/punchclock/:id",
  //     name: "punchclock-details",
  //     component: () => import("../components/subcomponents/Login.vue")
  // },
  {
    path: "/login",
    name: "Login",
    component: () => import("../components/subcomponents/Login.vue"),
  },
  {
    path: "/logout",
    name: "Logout",
    component: () => import("../components/subcomponents/SignOut.vue"),
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("../components/subcomponents/SignUp.vue"),
  },
  {
    path: "/logout",
    name: "Logout",
    component: () => import("../components/subcomponents/SignOut.vue"),
  },
  {
    path: "/entry/add",
    name: "add",
    component: () => import("../components/subcomponents/CreateEntry.vue"),
  },
  {
    path: "/room/add",
    name: "add",
    component: () => import("../components/subcomponents/CreateRoom.vue"),
  },
  {
    path: "/category/add",
    name: "add",
    component: () => import("../components/subcomponents/CreateCategory.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
