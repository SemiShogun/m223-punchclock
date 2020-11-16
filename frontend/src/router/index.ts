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
    path: "/entries/new",
    name: "Create Entry",
    component: () => import("../components/subcomponents/CreateEntry.vue"),
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
    path: "/room",
    name: "add",
    component: () => import("../components/RoomList.vue"),
  },
  {
    path: "/category",
    name: "add",
    component: () => import("../components/CategoryList.vue"),
  },
  {
    path: "/user",
    name: "userinfo",
    component: () => import("../components/UserList.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
