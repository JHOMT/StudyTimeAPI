package jhomt.com.studytimeapi.Domain.GlobalConfiguration;

public record DataListGlobalConfiguration(
        Integer id,
        Integer studentId,
        String studentName,
        Boolean usePomodoro,
        Boolean enableNotifications,
        Integer defaultPomodoroDuration,
        Integer defaultBreakDuration,
        Boolean showRanking,
        String theme
) {
    public DataListGlobalConfiguration(GlobalConfiguration globalConfiguration) {
        this(
                globalConfiguration.getId(),
                globalConfiguration.getStudent().getId(),
                globalConfiguration.getStudent().getName(),
                globalConfiguration.getUsePomodoro(),
                globalConfiguration.getEnableNotifications(),
                globalConfiguration.getDefaultPomodoroDuration(),
                globalConfiguration.getDefaultBreakDuration(),
                globalConfiguration.getShowRanking(),
                globalConfiguration.getTheme()
        );
    }
}
