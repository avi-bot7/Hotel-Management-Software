const a = document.getElementById("sub");
a.addEventListener("click", function () {
    let s1 = document.getElementById("name1").value;
    let s2 = document.getElementById("name2").value;
    if (s1 == "" || s2 == "") {
        alert("INPUT BOX CANT BE EMPTY");
        return;
    }
    else {
        let cop1 = s1;
        let cop2 = s2;
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        let l1 = s1.length;
        let l2 = s2.length;
        for (let i = 0; i < l1; i++) {
            for (let j = 0; j < l2; j++) {
                let ch1 = s1.charAt(i);
                let ch2 = s2.charAt(j);
                if (ch1 == ch2) {
                    let arr1 = s1.split("");
                    arr1.splice(i, 1);
                    let arr2 = s2.split("");
                    arr2.splice(j, 1);
                    s1 = arr1.join("");
                    s2 = arr2.join("");
                    l1 = s1.length;
                    l2 = s2.length;
                    i--;
                    break;
                }
            }
        }

        let count = s1.length + s2.length;
        let index = 0;
        let s = "FLAMES";
        while (s.length > 1) {
            index = (index + count - 1) % s.length;
            let arr3 = s.split("");
            arr3.splice(index, 1);
            s = arr3.join("");
        }
        switch (s) {
            case "F":
                document.getElementById("flame").innerHTML = "Relation:" + "Friends";
                break;
            case "L":
                document.getElementById("flame").innerHTML = "Relation:" + "Love";
                break;
            case "A":
                document.getElementById("flame").innerHTML = "Relation:" + "Affection";
                break;
            case "M":
                document.getElementById("flame").innerHTML = "Relation:" + "Marriage";
                break;
            case "E":
                document.getElementById("flame").innerHTML = "Relation:" + "ENEMIES";
                break;
            case "S":
                document.getElementById("flame").innerHTML = "Relation:" + "SIBLINGS";
                break;
            default:
                alert("WRONG INPUT !!");
        }
        let result = ((s1.length + s2.length) / (cop1.length + cop2.length)) * 100;
        if (result > 95)
            document.getElementById("prob").innerHTML = "Relaton Probability is " + 95 + "%";
        if (result < 5)
            document.getElementById("prob").innerHTML = "Relaton Probability is " + 5 + "%";
        else
            document.getElementById("prob").innerHTML = "Relaton Probability is " + result + "%";
    }
});


