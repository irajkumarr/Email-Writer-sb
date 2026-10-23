package com.rajkumartimalsina.emailwriter;

import com.google.genai.Client;
import com.google.genai.gaos.models.interactions.CreateModelInteraction;
import com.google.genai.gaos.models.interactions.Interaction;
import com.google.genai.gaos.models.interactions.InteractionsInput;
import com.google.genai.gaos.models.interactions.Model;
import com.google.genai.gaos.models.operations.CreateInteractionRequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailWriterService {

    private final Client client;

    public EmailWriterService(
            @Value("${gemini.api.key}") String geminiKey) {
        this.client = Client.builder()
                .apiKey(geminiKey)
                .build();
    }

    public String generateEmailReply(EmailRequest request) {

        String prompt = """
                You are a professional email assistant.
                
                Generate exactly ONE best possible reply to the email below.
                Do not provide multiple options, alternative versions, or drafts.
                Choose the most appropriate tone and wording based on the
                original email.
                
                Requirements:
                - Be clear, polite, natural, and professional.
                - Address the sender's message directly.
                - Keep the reply concise but complete.
                - Match the formality of the original email.
                - Do not explain your choices or provide commentary.
                - Do not include labels such as "Option 1", "Suggested Reply",
                  or "Here is the reply".
                - Return only the final email reply, ready to copy and send.
                - Include a greeting and sign-off only when appropriate.
                - Never invent facts, commitments, or details not provided
                  in the original email.
                
                Original email:
                %s
                """.formatted(request.getEmailContent());

        CreateModelInteraction params =
                CreateModelInteraction.builder()
                        .model(Model.of("gemini-3.6-flash"))
                        .input(InteractionsInput.of(prompt))
                        .build();

        Interaction interaction = client.interactions
                .create(CreateInteractionRequestBody.of(params))
                .interaction()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Gemini returned no interaction"));

        return interaction.outputText().orElse("");
    }
}