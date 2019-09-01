<#import "/spring.ftl" as spring />
<#import "general.ftl" as g />

<@g.page>
    ${userAccount.userName}
    ${userAccount.firstName}
    ${userAccount.lastName}
    ${userAccount.role}
    ${userAccount.status}
    ${userAccount.createdDate}

</@g.page>