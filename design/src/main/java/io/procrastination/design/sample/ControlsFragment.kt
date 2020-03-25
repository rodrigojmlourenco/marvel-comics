package io.procrastination.design.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import io.procrastination.design.R
import io.procrastination.design.extensions.asSuccess
import io.procrastination.design.extensions.createSnackbar

class ControlsFragment : Fragment() {

    private lateinit var btnPrimary: Button
    private lateinit var btnSecondary: Button
    private lateinit var btnPhantom: Button
    private lateinit var checkBox: CheckBox
    private lateinit var radioButton: RadioButton
    private lateinit var toggle: SwitchCompat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_material_controls, container, false).also { view ->
            btnPrimary = view.findViewById(R.id.btn_primary)
            btnSecondary = view.findViewById(R.id.btn_secondary)
            btnPhantom = view.findViewById(R.id.btn_phantom)
            checkBox = view.findViewById(R.id.checkbox)
            radioButton = view.findViewById(R.id.radio)
            toggle = view.findViewById(R.id.toggle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPrimary.setOnClickListener { createSnackbar("Clicked primary.").asSuccess().show() }
        btnSecondary.setOnClickListener { createSnackbar("Clicked secondary.").asSuccess().show() }
        btnPhantom.setOnClickListener { createSnackbar("Clicked phantom.").asSuccess().show() }

        checkBox.setOnCheckedChangeListener { _, checked ->
            createSnackbar("${if (checked) "checked" else "unchecked"} the checkbox.").asSuccess().show()
        }

        radioButton.setOnCheckedChangeListener { _, checked ->
            createSnackbar("${if (checked) "checked" else "unchecked"} the radio button.").asSuccess().show()
        }

        toggle.setOnCheckedChangeListener { _, checked ->
            createSnackbar("${if (checked) "checked" else "unchecked"} the toggle.").asSuccess().show()
        }
    }
}
