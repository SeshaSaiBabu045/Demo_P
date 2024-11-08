// $(document).ready(function() {
//     // Function to validate form data
//     function validateForm() {
//         var name = $('#userName').val().trim();
//         var email = $('#userEmail').val().trim();
//         var subject = $('#userSubject').val().trim();
//         var message = $('#userMessage').val().trim();

//         if (name === '' || email === '' || subject === '' || message === '') {
//             alert('Please fill out all fields.');
//             return false;
//         }

//         return true;
//     }

//     // Function to display users
//     function displayUsers(users) {
//         var userList = $('#userList');
//         userList.empty();

//         if (users && users.length > 0) {
//             var table = $('<table></table>').addClass('user-table');
//             var header = $('<tr></tr>').append(
//                 $('<th></th>').text('Name'),
//                 $('<th></th>').text('Email'),
//                 $('<th></th>').text('Subject'),
//                 $('<th></th>').text('Message'),
//                 $('<th></th>').text('Edit'),
//                 $('<th></th>').text('Delete')
//             );
//             table.append(header);

//             users.forEach(function(user) {
//                 var row = $('<tr></tr>').attr('data-id', user.id).append(
//                     $('<td></td>').text(user.name),
//                     $('<td></td>').text(user.email),
//                     $('<td></td>').text(user.subject),
//                     $('<td></td>').text(user.message),
//                     $('<td></td>').addClass('actions').append(
//                         $('<button></button>').addClass('edit').text('Edit').attr('data-id', user.id)
//                     ),
//                     $('<td></td>').addClass('actions').append(
//                         $('<button></button>').addClass('delete').text('Delete').attr('data-id', user.id)
//                     )
//                 );
//                 table.append(row);
//             });

//             userList.append(table);

//             // Edit button click handler
//             $('.edit').click(function() {
//                 var userId = $(this).attr('data-id');
//                 var user = users.find(function(u) { return u.id == userId; });

//                 // Populate form fields with user data
//                 $('#userId').val(userId); // Set userId hidden input field
//                 $('#userName').val(user.name);
//                 $('#userEmail').val(user.email);
//                 $('#userSubject').val(user.subject);
//                 $('#userMessage').val(user.message);
//                 $('#submitButton').val('Update'); // Change submit button text

//                 // Sequentially focus on each field to highlight it
//                 setTimeout(function() { $('#userName').focus().select(); }, 100);
//                 setTimeout(function() { $('#userEmail').focus().select(); }, 200);
//                 setTimeout(function() { $('#userSubject').focus().select(); }, 300);
//                 setTimeout(function() { $('#userMessage').focus().select(); }, 400);
//             });

//             // Delete button click handler
//             $('.delete').click(function() {
//                 var userId = $(this).attr('data-id');

//                 if (confirm('Are you sure you want to delete this user?')) {
//                     // Immediately remove the row from the table
//                     $('tr[data-id="' + userId + '"]').remove();

//                     // Send delete request to the server
//                     $.ajax({
//                         type: 'DELETE',
//                         url: '/bin/example/jcrnodes?userId=' + userId,
//                         success: function(response) {
//                             console.log('User deleted successfully');
//                             // Optionally update the user list with the server response
//                             displayUsers(response);
//                         },
//                         error: function(xhr, status, error) {
//                             console.error('Error deleting user:', error);
//                             // Optionally add the row back if there's an error
//                             alert('Failed to delete the user. Please try again.');
//                             // You may want to re-fetch and display users here to handle the error gracefully
//                         }
//                     });
//                 }
//             });

//         } else {
//             userList.text('No users found.');
//         }
//     }

//     // Fetch and display users on page load
//     $.ajax({
//         type: 'GET',
//         url: '/bin/example/jcrnodes',
//         success: function(response) {
//             displayUsers(response);
//         },
//         error: function(xhr, status, error) {
//             console.error('Error fetching users:', error);
//         }
//     });

//     // Form submit handler for both submit and update actions
//     $('#myForm').submit(function(event) {
//         event.preventDefault();

//         // Validate form data
//         if (!validateForm()) {
//             return;
//         }

//         var formData = $(this).serialize();
//         var userId = $('#userId').val();
//         var url = '/bin/example/jcrnodes';
//         var method = 'POST';

//         if (userId) {
//             method = 'PUT';
//             formData += '&userId=' + userId;
//         }

//         $.ajax({
//             type: method,
//             url: url,
//             data: formData,
//             success: function(response) {
//                 console.log('Form submitted successfully');
//                 // Reset form and reload users
//                 $('#myForm')[0].reset();
//                 $('#userId').val(''); // Clear userId after successful submission
//                 $('#submitButton').val('Submit'); // Change submit button text back
//                 displayUsers(response);
//             },
//             error: function(xhr, status, error) {
//                 console.error('Error submitting the form:', error);
//             }
//         });
//     });
// });
