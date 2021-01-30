export class Page<T> {
    content: Array<T>;
    totalElements: number;

    constructor(content, totalElemets) {
        this.content = content;
        this.totalElements = totalElemets;
    }
}