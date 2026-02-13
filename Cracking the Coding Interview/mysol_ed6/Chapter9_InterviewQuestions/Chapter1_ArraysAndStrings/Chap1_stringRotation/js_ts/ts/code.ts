function isSubstring(big: string, small: string): boolean {

  if (big.indexOf(small) >= 0) {
    return true;
  } else {
    return false;
  }
}

function isRotation(s1: string, s2: string): boolean {

  if (s1.length !== s2.length) {
    return false;
  }

  const combined: string = s1 + s1;

  return isSubstring(combined, s2);
}


// Tests
const tests: [string, string][] = [
  ["waterbottle", "erbottlewat"],
  ["hello", "llohe"],
  ["hello", "lloeh"],
  ["abc", "cab"],
  ["abc", "acb"]
];

let output: string = ">>> CTCI Chapter 1.9 – String Rotation <<<br><br>";

output += "<b>Solution</b><br>";

tests.forEach(pair => {
  const s1: string = pair[0];
  const s2: string = pair[1];
  output += `${s1} , ${s2} → ${isRotation(s1, s2)}<br>`;
});

(document.querySelector('#t1') as HTMLElement).innerHTML = output;