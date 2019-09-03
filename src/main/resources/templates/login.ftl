
<#import "general.ftl" as g />
<#import "/spring.ftl" as spring />

<@g.page>
<#if error??>
    <div class="uui-alert-messages fuchsia" role="alert" id="content-alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">
                    <span></span>
                    <span></span>
                </span>
        </button>
        <i class="fa fa-exclamation-triangle"></i>
        <strong>${error}</strong>
    </div>
</#if>


<div class="text-center" id="content">
    <div class="h1">
        <svg id="i-signin" viewBox="0 0 32 32" width="32" height="32" fill="none" stroke="currentcolor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
            <path d="M3 16 L23 16 M15 8 L23 16 15 24 M21 4 L29 4 29 28 21 28" />
        </svg>
        Sign in
    </div>
</div>

<div class="container my-5" >
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-5">
            <form action="/login/authentication" method="post">
                <div class="form-group">
                    <label for="inputLogin">Login: </label>
                    <input type="text" class="form-control" id="inputLogin" aria-describedby="emailHelp" name="login">
                    <small id="emailHelp" class="form-text text-muted"></small>
                </div>
                <div class="form-group">
                    <label for="inputPassword6">Password : </label>
                    <input type="password" class="form-control" id="inputPassword6" aria-describedby="emailHelp" name="password" ">
                    <small id="emailHelp" class="form-text text-muted"></small>
                </div>

                <div class="form-group">
                    <input type="submit" value="submit" class="btn btn-primary">
                </div>
            </form>
        </div>
    </div>
</div>
</@g.page>