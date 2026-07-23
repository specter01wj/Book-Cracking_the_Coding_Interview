class LinkedListNode {
    data: number;
    next: LinkedListNode | null = null;

    constructor(data: number) {
        this.data = data;
    }
}

class Chap2_removeDups {

    deleteDups(head: LinkedListNode | null): void {

        const set = new Set<number>();

        let previous: LinkedListNode | null = null;
        let current = head;

        while (current) {

            if (set.has(current.data)) {
                previous!.next = current.next;
            } else {
                set.add(current.data);
                previous = current;
            }

            current = current.next;
        }
    }

    deleteDupsNoBuffer(head: LinkedListNode | null): void {

        let current = head;

        while (current) {

            let runner = current;

            while (runner.next) {

                if (runner.next.data === current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }

            current = current.next;
        }
    }

    buildList(...values: number[]): LinkedListNode | null {

        if (values.length === 0) return null;

        const head = new LinkedListNode(values[0]);
        let current = head;

        for (let i = 1; i < values.length; i++) {
            current.next = new LinkedListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    listToString(head: LinkedListNode | null): string {

        if (!head) return "Empty";

        const arr: number[] = [];

        while (head) {
            arr.push(head.data);
            head = head.next;
        }

        return arr.join(" -> ");
    }
}

const test = new Chap2_removeDups();

let output = ">>> CTCI Chapter 2.1 - Remove Dups <<<br><br>";

const list1 = test.buildList(1,2,3,2,4,3,5,1);

output += "<b>Original</b><br>";
output += test.listToString(list1) + "<br>";

test.deleteDups(list1);

output += "<b>deleteDups</b><br>";
output += test.listToString(list1) + "<br><br>";

const list2 = test.buildList(1,2,3,2,4,3,5,1);

test.deleteDupsNoBuffer(list2);

output += "<b>deleteDupsNoBuffer</b><br>";
output += test.listToString(list2);

(document.querySelector("#t1") as HTMLElement).innerHTML = output;