// =========================================
// Solution 1: Separated logic
// =========================================
function oneEditAwaySeparated(first: string, second: string): boolean {
    if (first.length === second.length) {
        return oneEditReplace(first, second);
    } else if (first.length + 1 === second.length) {
        return oneEditInsert(first, second);
    } else if (first.length - 1 === second.length) {
        return oneEditInsert(second, first);
    }
    return false;
}

function oneEditReplace(s1: string, s2: string): boolean {
    let foundDiff = false;
    for (let i = 0; i < s1.length; i++) {
        if (s1[i] !== s2[i]) {
            if (foundDiff) return false;
            foundDiff = true;
        }
    }
    return true;
}

function oneEditInsert(s1: string, s2: string): boolean {
    let i = 0, j = 0;
    while (i < s1.length && j < s2.length) {
        if (s1[i] !== s2[j]) {
            if (i !== j) return false;
            j++;
        } else {
            i++; j++;
        }
    }
    return true;
}

// =========================================
// Solution 2: Merged logic (preferred)
// =========================================
function oneEditAway(first: string, second: string): boolean {
    if (Math.abs(first.length - second.length) > 1) return false;

    const shorter = first.length < second.length ? first : second;
    const longer  = first.length < second.length ? second : first;

    let i = 0, j = 0;
    let foundDiff = false;

    while (i < shorter.length && j < longer.length) {
        if (shorter[i] !== longer[j]) {
            if (foundDiff) return false;
            foundDiff = true;
            if (shorter.length === longer.length) i++;
        } else {
            i++;
        }
        j++;
    }
    return true;
}

// =========================================
// Tests + DOM output
// =========================================
const tests: [string, string][] = [
    ["pale", "ple"],
    ["pales", "pale"],
    ["pale", "bale"],
    ["pale", "bake"]
];

let output = ">>> CTCI Chapter 1.5 – One Away <<<br><br>";

output += "<b>Solution 1: oneEditAwaySeparated</b><br>";
tests.forEach(t => {
    output += `"${t[0]}" vs "${t[1]}" → ${oneEditAwaySeparated(t[0], t[1])}<br>`;
});

output += "<br><b>Solution 2: oneEditAway (merged)</b><br>";
tests.forEach(t => {
    output += `"${t[0]}" vs "${t[1]}" → ${oneEditAway(t[0], t[1])}<br>`;
});

(document.querySelector('#t1') as HTMLElement).innerHTML = output;