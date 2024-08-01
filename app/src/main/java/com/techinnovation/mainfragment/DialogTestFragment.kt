package com.techinnovation.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.techinnovation.mainfragment.dialogboxtesting.DialogBoxTexta

class DialogTestFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.openOkDialogbtn).setOnClickListener {
            DialogBoxTexta(
                onBind = {
it.createDialogBox(DIALOGTYPE.Ok)
                }
            ).show(parentFragmentManager, "DialogTexta")
        }

        view.findViewById<AppCompatButton>(R.id.deleteDialog).setOnClickListener {
            DialogBoxTexta(
                onBind = {
                    it.createDialogBox(type = DIALOGTYPE.DELETE)
                }
            ).show(childFragmentManager, "Delete")
        }
        view.findViewById<AppCompatButton>(R.id.okCancelForDialogTextTest).setOnClickListener {
            DialogBoxTexta(
                onBind = {
                    it.createDialogBox(type = DIALOGTYPE.DELETE)
                }
            ).show(childFragmentManager, "OkCancel")
        }
    }
        /*
        view.findViewById<MaterialButton>(R.id.okCancelText).setOnClickListener {
            showDialogTextOKCancel()
        }
        view.findViewById<Button>(R.id.delTest).setOnClickListener {
            showDialogTDeletea()
        }
        view.findViewById<MaterialButton>(R.id.deleteDialogText).setOnClickListener {
            showDialogTextDelete()
        }
        view.findViewById<MaterialButton>(R.id.extWayTowardsDialog).setOnClickListener {
            showDialogTextCancel()
        }


*/

    }

    /*

    private fun showDialog() {
        DialogBox.ok(
            msg = "message",
            type = DialogBox.ALERT,
            titleHeading = "title",
            titleOK = "OK",
            actionOK = {
                Toast.makeText(requireContext(), "Action Okay", Toast.LENGTH_SHORT).show()
            },
            onClose = {
                Toast.makeText(requireContext(), "Action Close", Toast.LENGTH_SHORT).show()
            }


        ).show(childFragmentManager)


    }

    private fun showDialogDelete() {
        DialogBox.delete(
            title = "delete",
            msg = "Once removed, it will be permanently deleted.",
            titleDeleteButton = "DeleteDone",
            titleCancelButton = "Cancel",
            onCancel = {
                Toast.makeText(requireContext(), "ActionCancel", Toast.LENGTH_SHORT).show()
            },
            onDelete = {
                Toast.makeText(requireContext(), "ActionCancel", Toast.LENGTH_SHORT).show()
            }
        ).show(childFragmentManager)

    }

    private fun showDialogCancel() {
        DialogBox.okCancel(
            type = "Alert!",
            titleHeading = "Alert Dialog",
            titleOK = "Done",
            titleCancel = "Cancel",
            msg = "OkCancel Dialog Selected",
            actionOK = {
                Toast.makeText(requireContext(), "ActionOk", Toast.LENGTH_SHORT).show()
            },
            actionCancel = {
                Toast.makeText(requireContext(), "ActionOkCancel", Toast.LENGTH_SHORT).show()
            },
            onClose = {
                Toast.makeText(requireContext(), "Close", Toast.LENGTH_SHORT).show()
            }
        ).show(childFragmentManager)
    }

    private fun showDialogTextOk() {

        DialogBoxTexta(
            onBind = {
                it.okDialogBox()
            }
        ).show(childFragmentManager)
    }
    private fun showDialogTextCancel() {

        DialogBoxTexta(
            onBind = {
                it.customizeOkCancelDialogBox()
            }
        ).show(childFragmentManager)
    }

    private fun showDialogTextOKCancel() {
        DialogBoxText(
            onBind = {
                it.customizeOkCancelDialogBox()
            }
        ).show(childFragmentManager)
    }
    private fun showOkNew()
    {
        DialogBoxTexta()
    }
    private fun showDialogTDeletea() {
        val deleteDialog = DialogBoxTexta(
            onDelete = { view ->
                Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show()
                // Handle delete action
            },
            onCancel = { view ->
                // Handle cancel action
                Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            },
            onClose = {
                // Handle close action
                Toast.makeText(requireContext(), "Close", Toast.LENGTH_SHORT).show()
            }
        )
        deleteDialog.show(childFragmentManager, "DeleteDialog")
    }


    private fun showDialogTextDelete() {
        DialogBoxText(
            onBind = {
                it.deleteDialogBoxText()
            }
        ).show(childFragmentManager)
    }

    //Do above like work
    */
    /*       DialogBoxText.ok(
               msg = "message",
               type = DialogBox.ALERT,
               titleHeading = "title",
               titleOK = "OK",
               actionOK = {
                   Toast.makeText(requireContext(), "Action Okay", Toast.LENGTH_SHORT).show()
               },
               onBind = {
                   it.okDialogBox()

                   it.tvTitle.setText(R.string.titletest)
               }
           ).show(childFragmentManager)*//*


}



*/
