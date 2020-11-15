export default function AuthHeader() {
  const user = JSON.parse(localStorage.getItem("bearer") || "{}");
  return user ? { Authorization: "Bearer " + user } : {};
}
