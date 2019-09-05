<#macro pager url page>

    <nav aria-label="Page navigation example">
        <ul class="pagination">
    <#list 1..page.totalPages as p>
        <#if p-1 == page.number>
            <li class="page-item active"><a class="page-link" href="#" tabindex="-1">${p}</a></li>
        <#else >
            <li class="page-item"><a class="page-link" href="${url}?page=${p-1}" tabindex="-1">${p}</a></li>
        </#if>
    </#list>
        </ul>
    </nav>

</#macro>