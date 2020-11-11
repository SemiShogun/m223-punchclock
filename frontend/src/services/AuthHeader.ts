export default function AuthHeader() {

    const user = JSON.parse(localStorage.getItem('bearer') || '{}');
    if (user) {
        return { Authorization: 'Bearer ' + user}
    } else {
        return {};
    }

}