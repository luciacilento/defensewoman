<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!-- Start Script -->
	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>	
	<!-- End Script -->

<c:if test="${not empty errore}">
<script type="text/javascript">$( window ).on( "load",function(){$('#errorModal').modal('show')});</script>
<div id="errorModal" class="modal fade " aria-hidden="true" tabindex="-1" role="dialog">
  <div class="modal-dialog alert-danger" role="document">
    <div class="modal-content" style="background-color: #f8d7da">
      <div class="modal-header" style="border-color: #f5c6cb;">
        <h5 class="modal-title">Errore</h5>        
        <button onclick="$('#errorModal').modal('hide');" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>${errore}</p>
      </div>     
    </div>
  </div>
</div>
</c:if>

<c:if test="${not empty successo}">
<script type="text/javascript">$( window ).on( "load",function(){$('#successModal').modal('show')});</script>
<div id="successModal" class="modal fade " aria-hidden="true" tabindex="-1" role="dialog">
  <div class="modal-dialog alert-success" role="document">
    <div class="modal-content" style="background-color: #d4edda;">
      <div class="modal-header" style="border-color: #c3e6cb;">
        <h5 class="modal-title">Completato!</h5>        
        <button onclick="$('#successModal').modal('hide');" type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>${successo}</p>
      </div>     
    </div>
  </div>
</div>
</c:if>
	




